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
    email: "prodan_raul14@yahoo.com",
    username: "prodi",
    nume: "Prodan",
    prenume: "Raul"
  };
  form: any = {};

  constructor(private tokenStorageService: TokenStorageService, private toastr: ToastrService, private userService: UserService) {
  }

  ngOnInit(): void {
    this.form = this.tokenStorageService.getUser();
  }

  getUserInfo(){
    // this.form.email="prodan_raul14@yahoo.com";
    // this.form.username="prodi";
    // this.form.nume="Prodan";
    // this.form.prenume="Raul";
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
