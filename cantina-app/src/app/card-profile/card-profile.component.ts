import {Component, Input, OnInit} from '@angular/core';
import {UserService} from "../services/user.service";

@Component({
  selector: 'tr[app-card-profile]',
  templateUrl: './card-profile.component.html',
  styleUrls: ['./card-profile.component.css']
})
export class CardProfileComponent implements OnInit {
  @Input()
  card: any;

  constructor(private userService: UserService) {
  }

  ngOnInit(): void {
  }

  deleteCard() {
    this.userService.deleteUserCard(this.card.id).subscribe((data) => {
        console.log(data);
        window.location.reload();
      },
      error => {
        console.log(error);
      });
  }
}
