import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {RequestHeaderConstants} from "./requestHeader.constants";
import {Observable} from "rxjs";
import {Product} from "../../shared/model/Product";
import {UserAuthService} from "./user-auth.service";

@Injectable({
  providedIn: 'root'
})
export class ProductManagerService {

  PATH_OF_API = "http://localhost:8080/admin/product";

  constructor(private httpClient: HttpClient,
              public userAuthService: UserAuthService,
              public requestHeaderConstants: RequestHeaderConstants) {
  }

  public getAllProducts(): Observable<Product[]> {

    return this.httpClient.get<Product[]>(this.PATH_OF_API, {headers: this.requestHeaderConstants.Authorization()});
  }

  public save(product: Product): Observable<Product> {
    if (product.id) {
      product.modifiedBy = this.userAuthService.getUserDetail().username;
      product.modifiedDate = new Date();
      return this.update(product);
    } else {
      product.createBy = this.userAuthService.getUserDetail().username;
      product.createDate = new Date();
      return this.create(product);
    }
  }

  public create(product: Product): Observable<Product> {

    return this.httpClient.post<Product>(this.PATH_OF_API, product, {headers: this.requestHeaderConstants.Authorization()});
  }

  public update(product: Product): Observable<Product> {

    return this.httpClient.put<Product>(this.PATH_OF_API, product, {headers: this.requestHeaderConstants.Authorization()});
  }

  public delete(id: number) {

    return this.httpClient.delete(this.PATH_OF_API + '?id=' + id, {headers: this.requestHeaderConstants.Authorization()});
  }

  getProductByNameOrId(value: string): Observable<Product[]> {
    return this.httpClient.get<Product[]>(this.PATH_OF_API + '/' + value, {headers: this.requestHeaderConstants.Authorization()});
  }
}
