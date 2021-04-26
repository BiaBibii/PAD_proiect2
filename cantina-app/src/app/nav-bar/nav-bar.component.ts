import {Component, OnInit} from '@angular/core';
import {UserService} from "../services/user.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {
  isLogIn = false;
  isAdmin = false;

  constructor( private router: Router,private userService: UserService) {
  }

  ngOnInit(): void {
    this.userService.isLogin.subscribe(isLogIn => {
      console.log(isLogIn);
      this.isLogIn = isLogIn;
    });

    this.userService.roleAs.subscribe(role => {
      console.log(role);
      this.isAdmin = role === "ROLE_ADMIN";
    })
  }

  logOut() {
    this.userService.logOut();
    this.router.navigate(['acasa']);
  }


}
