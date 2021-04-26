import {AfterContentInit, AfterViewInit, Component, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {UserService} from "../services/user.service";



@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit{
  isLogIn=false;
  isAdmin=false;

  constructor(private userService: UserService) {
  }

  ngOnInit(): void {
    this.isLogIn=this.userService.isLoggedIn();
    if(this.userService.getRole()==="ROLE_ADMIN")
      this.isAdmin=true;

  }



  // ngOnChanges(changes: SimpleChanges): void {
  //   console.log("So schimbat");
  //   window.location.reload();
  // }


}
