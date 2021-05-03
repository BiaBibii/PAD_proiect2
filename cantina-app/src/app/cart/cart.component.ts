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

  constructor(private cartService: CartService) {
  }

  ngOnInit(): void {
    this.cartService.getCartItems().subscribe((items) => {
        this.items=items;
        console.log(this.items);
      },
      error => {
      console.log(error);
      });
  }

}
