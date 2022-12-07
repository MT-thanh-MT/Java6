import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {RequestHeaderConstants} from "./requestHeader.constants";
import {Observable} from "rxjs";
import {Order, OrderView} from "../../shared/model/Order";

@Injectable({
  providedIn: 'root'
})
export class OrderManagerService {
  PATH_OF_API = "http://localhost:8080/admin/order";

  constructor(private httpClient: HttpClient,
              private requestHeaderConstants: RequestHeaderConstants) { }

  public getOrderByStatus(status: string): Observable<Order[]> {
    return this.httpClient.get<Order[]>(this.PATH_OF_API + '?status=' + status, {headers: this.requestHeaderConstants.Authorization()});
  };

  update(order: Order): Observable<Order> {
    return this.httpClient.put<Order>(this.PATH_OF_API, order, { headers: this.requestHeaderConstants.Authorization() });
  }
}
