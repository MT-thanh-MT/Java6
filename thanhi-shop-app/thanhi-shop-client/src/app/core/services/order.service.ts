import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Order, OrderView} from "../../shared/model/Order";
import {Observable} from "rxjs";
import {RequestHeaderConstants} from "./requestHeader.constants";

@Injectable({
  providedIn: 'root'
})
export class OrderService {
  PATH_OF_API = "http://localhost:8080/api/order";

  constructor(private httpClient: HttpClient,
              private requestHeaderConstants: RequestHeaderConstants) { }

  public createOrder(oder: Order): Observable<Order> {
    return this.httpClient.post<Order>(this.PATH_OF_API, oder, { headers: this.requestHeaderConstants.Authorization() });
  }

  public getOrderViewByUsername(username: string): Observable<OrderView[]> {
    return this.httpClient.get<OrderView[]>(this.PATH_OF_API + '/' + username, {headers: this.requestHeaderConstants.Authorization()});
  };
}
