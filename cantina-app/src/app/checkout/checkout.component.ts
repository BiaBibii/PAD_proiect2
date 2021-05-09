import {Component, OnInit} from '@angular/core';
import {not} from "rxjs/internal-compatibility";
import {CheckoutService} from "../services/checkout.service";
import {UserService} from "../services/user.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {
  cartItems: any[] | any;
  cardList: any[] | any;
  defaultCardId: any;

  constructor(private userService: UserService, private checkoutService: CheckoutService, private router:Router) {
  }

  ngOnInit(): void {
    this.checkoutService.goToCheckout().subscribe((data: any) => {
        this.cartItems = data.cartItemList;
        this.cardList = data.userPaymentList;
        console.log(this.cartItems);
        console.log(this.cardList);
      },
      error => {
        console.log(error);
      });
  }




  setDefaultCard(cardId: any) {
    console.log(cardId);
    this.userService.setDefaultCard(cardId).subscribe(data => {
        console.log(data);
      },
      error => {
        console.log(error);
      });
  }

  placeOrder() {
    this.checkoutService.placeOrder().subscribe(data =>{
    console.log(data);
    this.router.navigate(['acasa']);
    },
      error => {
      console.log(error);
      })
  }
}
