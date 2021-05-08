import { Component, OnInit } from '@angular/core';
import {not} from "rxjs/internal-compatibility";

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {

  cardNumber: string|any;
  cardHolder: string;
  expirationMonth: string;
  expirationYear: string;
  ccv: number;

  card=false;
  card_val=false;

  constructor() { }

  ngOnInit(): void {
  }

  onCardChange() {
    this.card=!this.card;
    console.log(this.card);
  }

  onThereChange() {
    this.card=!this.card;
    console.log(this.card);
  }

  submit() {
    if(this.card)
      this.card_val=true;
    else
      this.card_val=false;
  }


  processPayment() {
    console.log("mergeee");
  }
}
