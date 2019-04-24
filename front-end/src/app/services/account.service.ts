import { Injectable } from '@angular/core';
import {User} from "../model/model.user";
import {Http} from "@angular/http";
import {AppComponent} from "../app.component";
import {Observable} from "rxjs";

@Injectable()
export class AccountService {
  constructor(public http: Http) { }

  createAccount(user:User){
    return this.http.post(AppComponent.API_URL+'/account/register',user).map(resp=>resp.json());
  }

  editAccount(user:User){
    return this.http.post(AppComponent.API_URL+'/account/edit',user).map(resp=>resp.json());
  }
  changePass(user:User){
    return this.http.post(AppComponent.API_URL+'/account/edit',user).map(resp=>resp.json());
  }
  testMethod(oldPas: string,newPas: string,retypePas: string) {
    return this.http.get(AppComponent.API_URL+'/account/Test').map(resp=>resp.json());
  }
}
