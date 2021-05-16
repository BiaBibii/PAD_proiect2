import {Component, OnInit} from '@angular/core';
import {TokenStorageService} from "../services/token-storage.service";
import {ToastrService} from "ngx-toastr";
import {UserService} from "../services/user.service";
import {User} from "../models/user";

@Component({
  selector: 'app-profile-edit-form',
  templateUrl: './profile-edit-form.component.html',
  styleUrls: ['./profile-edit-form.component.css']
})
export class ProfileEditFormComponent implements OnInit {

  newUser: User|any={
    email:"",
    username:"",
    firstName:"",
    lastName:"",
    password:""
  };

  form: any = {};

  constructor(private tokenStorageService: TokenStorageService, private toastr: ToastrService, private userService: UserService) {
  }

  ngOnInit(): void {
    this.form = this.tokenStorageService.getUser();
    console.log(this.tokenStorageService.getToken());
  }

  getUserInfo(){

     this.newUser.email= (<HTMLInputElement>document.getElementById("email")).value;
     this.newUser.username=(<HTMLInputElement>document.getElementById("username")).value;
     this.newUser.firstName=(<HTMLInputElement>document.getElementById("firstname")).value;
     this.newUser.lastName=(<HTMLInputElement>document.getElementById("lastname")).value;
     this.newUser.password=(<HTMLInputElement>document.getElementById("password")).value;

  }

  updateUserInfo() {
    this.getUserInfo();
    console.log(this.newUser);
    this.userService.updateUserInfo(this.newUser).subscribe((data) => {
        console.log(data)
      },
      error => {

        console.log(error);
      });
  }
}
