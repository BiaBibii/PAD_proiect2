import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {User} from "../models/user";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private url='http://localhost:8080/api/';

  constructor(private http:HttpClient) { }

  register(user: User){
    return this.http.post<User>(this.url+'register',user);
  }

  logIn(user: User){
    return this.http.post<User>(this.url+'login',user);
  }

  logOut(){

  }

  forgetPassword(){

  }

}
