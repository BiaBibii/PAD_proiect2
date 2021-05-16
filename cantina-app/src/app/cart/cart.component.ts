import {Component, OnInit} from '@angular/core';
import {CartService} from "../services/cart.service";
import {Product} from "../models/product";

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  img = "/assets/img/contact_top.jpg"
  items: Product[] | any;
  cartIsEmpty=false;

  constructor(private cartService: CartService) {
  }

  ngOnInit(): void {
    this.cartService.getCartItems().subscribe((items) => {
        this.items=items;
        if(this.items.length==0)
          this.cartIsEmpty=true;
        console.log(this.cartIsEmpty);

      },
      error => {
      console.log(error);

      });
  }

}
