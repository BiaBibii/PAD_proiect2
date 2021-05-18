import {Component, OnInit} from '@angular/core';
import {UserService} from "../services/user.service";
import {ToastrService} from "ngx-toastr";
import {Router} from "@angular/router";

@Component({
  selector: 'app-forget-password',
  templateUrl: './forget-password.component.html',
  styleUrls: ['./forget-password.component.css']
})
export class ForgetPasswordComponent implements OnInit {
  email: string | any;
  sentMail: boolean | undefined;
  user: any = {
    email: ""
  };

  constructor(private router: Router, private toastr: ToastrService, private userService: UserService) {
  }

  ngOnInit(): void {
  }

  onSubmit(form: any) {
    console.log(form.email);
    this.user.email = form.email;
    this.userService.forgetPassword(this.user).subscribe(() => { // success path
        // to do

      },
      error => { // error path
        if (error.error.error.message == "Unexpected token e in JSON at position 0") {
          this.sentMail = true;
          this.router.navigate(['login']);
          this.toastr.success("Check your email for the new password");
        } else {
          console.log(error);
          this.toastr.error("Incorrect email");
        }
      });
  }
}
