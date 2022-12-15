import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {RequestHeaderConstants} from "./requestHeader.constants";
import {Observable} from "rxjs";
import {Category} from "../../shared/model/Category";

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  public currentCategoryName: string = 'All category';

  PATH_OF_API = "http://localhost:8080/api/category";

  constructor(private httpClient: HttpClient,
              public requestHeaderConstants: RequestHeaderConstants) { }

  public getCategories(): Observable<Category[]>{
    return this.httpClient.get<Category[]>(this.PATH_OF_API,{ headers: this.requestHeaderConstants.NO_AUTH });
  }

}
