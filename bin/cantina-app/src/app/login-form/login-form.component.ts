import { Component, OnInit } from '@angular/core';
import {LoginFormComponentsService} from "./login-form.components.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent implements OnInit {
  email: string | undefined;
  password:string |undefined;
  loggedIn:boolean |undefined;
  constructor(public loginService: LoginFormComponentsService, private router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
  }

  onSubmit(form: any) {
  if(this.loginService.password === form.password && this.loginService.email === form.email){
    this.loggedIn=true;
    console.log("Sunt logat")
    this.router.navigate(['/acasa']);
   }
  else this.loggedIn=false;
  }

}
