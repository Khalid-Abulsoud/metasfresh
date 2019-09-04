package de.metas.rest_api.bpartner_pricelist;

import java.time.LocalDate;
import java.util.Optional;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.I_M_PriceList;
import org.compiere.model.I_M_ProductPrice;
import org.springframework.stereotype.Service;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

import de.metas.bpartner.BPartnerId;
import de.metas.bpartner.service.BPartnerQuery;
import de.metas.bpartner.service.IBPartnerDAO;
import de.metas.currency.CurrencyCode;
import de.metas.currency.ICurrencyDAO;
import de.metas.lang.SOTrx;
import de.metas.location.CountryId;
import de.metas.location.ICountryDAO;
import de.metas.money.CurrencyId;
import de.metas.pricing.PriceListId;
import de.metas.pricing.PriceListVersionId;
import de.metas.pricing.PricingSystemId;
import de.metas.pricing.service.IPriceListDAO;
import de.metas.pricing.service.PriceListsCollection;
import de.metas.product.IProductBL;
import de.metas.product.ProductId;
import de.metas.rest_api.bpartner_pricelist.response.JsonResponsePrice;
import de.metas.rest_api.bpartner_pricelist.response.JsonResponsePriceList;
import de.metas.rest_api.utils.IdentifierString;
import de.metas.rest_api.utils.IdentifierString.Type;
import de.metas.util.Services;
import lombok.NonNull;

/*
 * #%L
 * de.metas.business.rest-api-impl
 * %%
 * Copyright (C) 2019 metas GmbH
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 2 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/gpl-2.0.html>.
 * #L%
 */

@Service
class BpartnerPriceListEndpointService
{
	private final IBPartnerDAO bpartnersRepo = Services.get(IBPartnerDAO.class);
	private final IPriceListDAO priceListsRepo = Services.get(IPriceListDAO.class);
	private final ICountryDAO countriesRepo = Services.get(ICountryDAO.class);
	private final IProductBL productsService = Services.get(IProductBL.class);
	private final ICurrencyDAO currenciesRepo = Services.get(ICurrencyDAO.class);

	public JsonResponsePriceList getPriceList(
			@NonNull final IdentifierString bpartnerIdentifier,
			@NonNull final SOTrx soTrx,
			@NonNull final String countryCode,
			@NonNull final LocalDate date)
	{
		final CountryId countryId = countriesRepo.getCountryIdByCountryCode(countryCode);

		final BPartnerId bpartnerId = retrieveBPartnerId(bpartnerIdentifier).orElse(null);
		if (bpartnerId == null)
		{
			throw new AdempiereException("No BPartner found for " + bpartnerIdentifier);
		}

		final PricingSystemId pricingSystemId = bpartnersRepo.retrievePricingSystemId(bpartnerId, soTrx);
		if (pricingSystemId == null)
		{
			throw new AdempiereException("No pricing system defined for " + bpartnerId);
		}

		final PriceListsCollection priceLists = priceListsRepo.retrievePriceListsCollectionByPricingSystemId(pricingSystemId);
		final I_M_PriceList priceList = priceLists.getPriceList(countryId, soTrx).orElse(null);
		if (priceList == null)
		{
			throw new AdempiereException("No pricing list found for countryCode=" + countryCode + " and soTrx=" + soTrx);
		}

		final CurrencyId currencyId = CurrencyId.ofRepoId(priceList.getC_Currency_ID());
		final CurrencyCode currencyCode = currenciesRepo.getCurrencyCodeById(currencyId);

		final PriceListId priceListId = PriceListId.ofRepoId(priceList.getM_PriceList_ID());
		final PriceListVersionId priceListVersionId = priceListsRepo.retrievePriceListVersionId(priceListId, date);

		final ImmutableList<I_M_ProductPrice> productPriceRecords = priceListsRepo.retrieveProductPrices(priceListVersionId)
				.collect(ImmutableList.toImmutableList());

		final ImmutableSet<ProductId> productIds = productPriceRecords.stream()
				.map(productPrice -> ProductId.ofRepoId(productPrice.getM_Product_ID()))
				.collect(ImmutableSet.toImmutableSet());

		final ImmutableMap<ProductId, String> productValues = productsService.getProductValues(productIds);

		final ImmutableList<JsonResponsePrice> prices = productPriceRecords.stream()
				.map(productPrice -> toJsonResponsePrice(productPrice, productValues, currencyCode))
				.collect(ImmutableList.toImmutableList());

		return JsonResponsePriceList.builder()
				.prices(prices)
				.build();
	}

	private static JsonResponsePrice toJsonResponsePrice(
			@NonNull final I_M_ProductPrice productPrice,
			@NonNull final ImmutableMap<ProductId, String> productValues,
			@NonNull final CurrencyCode currencyCode)
	{
		final ProductId productId = ProductId.ofRepoId(productPrice.getM_Product_ID());

		return JsonResponsePrice.builder()
				.productId(productId)
				.productCode(productValues.get(productId))
				.price(productPrice.getPriceStd())
				.currencyCode(currencyCode)
				.build();
	}

	private Optional<BPartnerId> retrieveBPartnerId(final IdentifierString bpartnerIdentifier)
	{
		final BPartnerQuery query = createBPartnerQuery(bpartnerIdentifier);
		return bpartnersRepo.retrieveBPartnerIdBy(query);
	}

	private static BPartnerQuery createBPartnerQuery(@NonNull final IdentifierString bpartnerIdentifier)
	{
		final Type type = bpartnerIdentifier.getType();
		if (Type.METASFRESH_ID.equals(type))
		{
			return BPartnerQuery.builder()
					.bPartnerId(bpartnerIdentifier.asMetasfreshId(BPartnerId::ofRepoId))
					.build();
		}
		else if (Type.EXTERNAL_ID.equals(type))
		{
			return BPartnerQuery.builder()
					.externalId(bpartnerIdentifier.asExternalId())
					.build();
		}
		else if (Type.VALUE.equals(type))
		{
			return BPartnerQuery.builder()
					.bpartnerValue(bpartnerIdentifier.asValue())
					.build();
		}
		else if (Type.GLN.equals(type))
		{
			return BPartnerQuery.builder()
					.gln(bpartnerIdentifier.asGLN())
					.build();
		}
		else
		{
			throw new AdempiereException("Invalid bpartnerIdentifier: " + bpartnerIdentifier);
		}
	}
}
