import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {RequestHeaderConstants} from "./requestHeader.constants";
import {Observable} from "rxjs";
import {Role} from "../../shared/model/Role";

@Injectable({
  providedIn: 'root'
})
export class RoleService {
  PATH_OF_API = "http://localhost:8080";

  constructor(private httpClient: HttpClient,
              private requestHeaderConstants: RequestHeaderConstants) { }

  public getAll(): Observable<Role[]>{
    return this.httpClient.get<Role[]>(this.PATH_OF_API + "/api/role", { headers: this.requestHeaderConstants.Authorization() });
  }
}
