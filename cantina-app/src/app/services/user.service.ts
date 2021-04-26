import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {User} from "../models/user";
import {Observable} from "rxjs";


@Injectable({
  providedIn: 'root'
})
export class UserService {
  private url='http://localhost:8080/api/';
  isLogin = false;
  roleAs: string | null | undefined;

  constructor(private http:HttpClient) { }

  register(user: User){
    return this.http.post<User>(this.url+'register',user);
  }

  logIn(user: User){
    return this.http.post<User>(this.url+'login',user);
  }
  logInSuccess(username: string){
  this.isLogin = true;
  if(username=="admin")
    this.roleAs ="ROLE_ADMIN";
  else
    this.roleAs ="ROLE_USER";
  localStorage.setItem('STATE', 'true');
  localStorage.setItem('ROLE', this.roleAs);
  }

  logOut(){
    this.isLogin = false;
    this.roleAs = '';
    localStorage.setItem('STATE', 'false');
    localStorage.setItem('ROLE', '');
    return this.http.get(this.url+'logout');
  }

  isLoggedIn() {
    const loggedIn = localStorage.getItem('STATE');
    if (loggedIn == 'true')
      this.isLogin = true;
    else
      this.isLogin = false;
    return this.isLogin;
  }

  getRole() {
    this.roleAs = localStorage.getItem('ROLE');
    return this.roleAs;
  }

  forgetPassword(email: string){
    return this.http.post(this.url+'forgetPassword',email)
  }

}
