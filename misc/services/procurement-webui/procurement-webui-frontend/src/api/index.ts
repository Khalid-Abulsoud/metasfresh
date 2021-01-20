import axios, { AxiosResponse } from 'axios';

import { createServer } from 'miragejs';

export function loadMirageInDev(): void {
  if (process.env.NODE_ENV === 'development') {
    createServer({
      routes() {
        console.log('MIRAGE listening now...');

        this.namespace = '/rest';
        this.urlPrefix = 'http://localhost:3000';

        this.get('/dailyReport/:date', (schema, request) => {
          const date = request.params.date;
          const products = [
            {
              confirmedByUser: true,
              packingInfo: 'packing info 1',
              productId: '1234',
              productName: 'Broccoli',
              qty: 2,
            },
            {
              confirmedByUser: false,
              packingInfo: 'packing info 2',
              productId: '5678',
              productName: 'Lauch grun',
              qty: 4,
            },
            {
              confirmedByUser: true,
              packingInfo: 'packing info 3',
              productId: '9101',
              productName: 'Apfel',
              qty: 33,
            },
            {
              confirmedByUser: true,
              packingInfo: 'packing info 4',
              productId: '1213',
              productName: 'Kartoffel',
              qty: 99,
            },
            {
              confirmedByUser: true,
              packingInfo: 'packing info 5',
              productId: '1415',
              productName: 'Zweibel',
              qty: 105,
            },
          ];

          const elements = Math.floor(Math.random() * Math.floor(products.length));
          const pickedProducts = products.sort(() => 0.5 - Math.random()).slice(0, elements);

          return {
            date,
            dayCaption: 'Daycaption',
            products: pickedProducts,
          };
        });

        this.get('/session', () => {
          return {
            countUnconfirmed: 2,
            date: '01-12-2021',
            dayCaption: 'Day',
            email: 'string',
            language: 'de_DE',
            loggedIn: true,
            loginError: 'string',
            week: 'string',
            weekCaption: 'Week',
          };
        });

        this.post('/session/confirmDataEntry', () => {
          return {};
        });
      },
    });
  }
}

export function fetchDailyReport(date: string): Promise<AxiosResponse> {
  return axios.get(`/rest/dailyReport/${date}`);
}

export function postDailyReport(data: unknown): Promise<AxiosResponse> {
  return axios.post(`/rest/dailyReport`, data);
}

export function fetchWeeklyReport(weekYear: string): Promise<AxiosResponse> {
  return axios.get(`/rest/weeklyReport/${weekYear}`);
}

export function confirmDataEntry(): Promise<AxiosResponse> {
  return axios.post(`/rest/session/confirmDataEntry`);
}

export function getMessages(): Promise<AxiosResponse> {
  return axios.get('/rest/i18n/messages');
}

/** products-rest-controller */
export function favoriteAdd(data: Array<string>): Promise<AxiosResponse> {
  return axios.post(`/rest/products/favorite/add`, { productIds: data });
}

export function favoriteRemove(data: Array<string>): Promise<AxiosResponse> {
  return axios.post(`/rest/products/favorite/remove`, { productIds: data });
}

export function getNotFavorite(): Promise<AxiosResponse> {
  return axios.get(`/rest/products/notFavorite`);
}

export function logoutRequest(): Promise<AxiosResponse> {
  return axios.get(`/rest/session/logout`);
}

export function loginRequest(username: string, password: string): Promise<AxiosResponse> {
  return axios.post(`/rest/session/login`, {
    email: username,
    password,
  });
}

export function passwordResetRequest(email: string): Promise<AxiosResponse> {
  return axios.get(`/rest/session/resetUserPassword?email=${email}`, {
    validateStatus: () => {
      // returning true so that we can get the error message
      // TODO: This is temporary, as in the final solution we will have 400's for errors - not 500
      return true;
    },
  });
}

export function passwordResetConfirm(token: string): Promise<AxiosResponse> {
  return axios.get(`/rest/session/resetUserPasswordConfirm?token=${token}`, {
    validateStatus: () => true,
  });
}

export function getUserSession(): Promise<AxiosResponse> {
  return axios.get(`/rest/session/`);
}

export function infoMessages(): Promise<AxiosResponse> {
  return axios.get(`/rest/infoMessages`);
}
