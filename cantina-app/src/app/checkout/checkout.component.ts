import {Component, Input, OnInit} from '@angular/core';
import {not} from "rxjs/internal-compatibility";
import {CheckoutService} from "../services/checkout.service";
import {UserService} from "../services/user.service";
import {Router} from "@angular/router";
import {ToastrService} from "ngx-toastr";
import {forEachToken} from "tslint";

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {
  cartItems: any[] | any;
  cardList: any[] | any;
  defaultCardId: any;
  private selectedCard=false;


  constructor(private toastr: ToastrService, private userService: UserService, private checkoutService: CheckoutService, private router: Router) {
  }

  ngOnInit(): void {
    this.checkoutService.goToCheckout().subscribe((data: any) => {
        this.cartItems = data.cartItemList;
        this.cardList = data.userPaymentList;
        console.log(this.cartItems);
        console.log(this.cardList);
        for (let i = 0; i < this.cardList.length; i++) {
          if (this.cardList[i].default_payment == 1)
            this.selectedCard = true;
        }
      },
      error => {
        console.log(error);
      });
  }


  setDefaultCard(cardId: any) {
    console.log(cardId);
    this.userService.setDefaultCard(cardId).subscribe(data => {
        console.log(data);
        this.selectedCard = true;
      },
      error => {
        console.log(error);
      });
  }

  placeOrder() {
    if(this.selectedCard)
    {
      this.router.navigate(['/acasa']);
      this.toastr.success("Your order was placed! <3");
    }
    this.checkoutService.placeOrder().subscribe(data => {

      },
      error => {
        this.toastr.error("You need a card to pay!");
      })
  }
}
