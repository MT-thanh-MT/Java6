import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {RequestHeaderConstants} from "./requestHeader.constants";
import {Observable} from "rxjs";
import {Product} from "../../shared/model/Product";

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  PATH_OF_API = "http://localhost:8080/api/product";

  constructor(private httpClient: HttpClient,
              public requestHeaderConstants: RequestHeaderConstants) { }

  public getProducts(cid?: number): Observable<Product[]> {

    return this.httpClient.get<Product[]>(this.PATH_OF_API + '?cid=' + cid,{ headers: this.requestHeaderConstants.NO_AUTH });
  }

  getProductById(id: number):Observable<Product> {
    return this.httpClient.get<Product>(this.PATH_OF_API + '/' + id, { headers: this.requestHeaderConstants.NO_AUTH });
  }

  getRelatedProduct(subCateId: number) {
    return this.httpClient.get<Product[]>(this.PATH_OF_API + '/subcategory' + '?scid=' + subCateId,{ headers: this.requestHeaderConstants.NO_AUTH });
  }
}
