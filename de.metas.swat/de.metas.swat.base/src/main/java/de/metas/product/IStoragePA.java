package de.metas.product;

/*
 * #%L
 * de.metas.swat.base
 * %%
 * Copyright (C) 2015 metas GmbH
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 2 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-2.0.html>.
 * #L%
 */


import java.math.BigDecimal;
import java.util.Collection;

import org.adempiere.util.ISingletonService;
import org.compiere.model.I_M_Storage;

public interface IStoragePA extends ISingletonService {

	Collection<I_M_Storage> retrieveStorages(int productId, String trxName);

	int retrieveWarehouseId(I_M_Storage storage, String trxName);

	BigDecimal retrieveQtyAvailable(int M_Warehouse_ID, int M_Locator_ID,
			int M_Product_ID, int M_AttributeSetInstance_ID, String trxName);

	BigDecimal retrieveQtyOrdered(int productId, int warehouseId);

}