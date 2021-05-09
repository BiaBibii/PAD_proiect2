import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {ICardDetails} from "ng-payment-card/lib/domain/i-card-details";
import {UserService} from "../services/user.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-add-new-card',
  templateUrl: './add-new-card.component.html',
  styleUrls: ['./add-new-card.component.css']
})
export class AddNewCardComponent implements OnInit {


  card:any={};

  constructor(private userService: UserService, private router:Router) {
  }

  ngOnInit(): void {
  }

  setCard(cc: ICardDetails){
    this.card.cardName=cc.cardHolder;
    this.card.cardNumber=cc.cardNumber;
    this.card.cvc=cc.ccv;
    this.card.expiryYear=cc.expirationYear;
    this.card.expiryMonth=cc.expirationMonth;
    this.card.holderName=cc.cardHolder;
  }

  addNewCard(cc: ICardDetails) {
    this.setCard(cc);

    this.userService.addUserCard(this.card).subscribe((data) => {
        console.log(data);
        this.router.navigate(['profile']);
      },
      error => {
        console.log(error);
      });
  }
}
