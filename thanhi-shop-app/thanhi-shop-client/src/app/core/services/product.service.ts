import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {RequestHeaderConstants} from "./requestHeader.constants";
import {Observable} from "rxjs";
import {Product} from "../../shared/model/Product";
import {SearchRequestDTO} from "../../shared/model/SearchRequestDTO";

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

  searchProduct(value: string): Observable<Product[]> {
    let searchRequestDTO: SearchRequestDTO = new SearchRequestDTO();
    searchRequestDTO.text = value;
    searchRequestDTO.fields = ["name"];
    return this.httpClient.post<Product[]>(this.PATH_OF_API + '/search', searchRequestDTO, {headers: this.requestHeaderConstants.Authorization()});
  }

  getProductById(id: number):Observable<Product> {
    return this.httpClient.get<Product>(this.PATH_OF_API + '/' + id, { headers: this.requestHeaderConstants.NO_AUTH });
  }

  getRelatedProduct(subCateId: number) {
    return this.httpClient.get<Product[]>(this.PATH_OF_API + '/subcategory' + '?scid=' + subCateId,{ headers: this.requestHeaderConstants.NO_AUTH });
  }
}
