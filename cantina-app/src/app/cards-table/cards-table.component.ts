import { Component, OnInit } from '@angular/core';
import {UserService} from "../services/user.service";

@Component({
  selector: 'app-cards-table',
  templateUrl: './cards-table.component.html',
  styleUrls: ['./cards-table.component.css']
})
export class CardsTableComponent implements OnInit {
  cardList: any = [];

  constructor(private userService:UserService) { }

  ngOnInit(): void {
    this.userService.getCards().subscribe((data) => {
        this.cardList=data;
        console.log(this.cardList);
      },
      error => {
        console.log(error);
      })
  }


}
