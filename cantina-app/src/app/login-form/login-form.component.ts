import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {UserService} from "../services/user.service";
import {User} from "../models/user";

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent implements OnInit {

  password:string |undefined;
  loggedIn:boolean |undefined;
  username: string |undefined;

  private user: User|undefined;
  private error: string|undefined;

  constructor(private userService: UserService, private router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
  }

  onSubmit(form: any) {
    // @ts-ignore
    this.user=new User(form.username,form.password);

    console.log(this.user);
      console.log(this.loggedIn);
      this.userService.logIn(this.user).subscribe( customers => { // success path
          // to do
          this.loggedIn=true;
          console.log(this.loggedIn);
          this.router.navigate(['acasa']);
        },
        error => { // error path
          console.log(error.error.message);
        });






   }



}
