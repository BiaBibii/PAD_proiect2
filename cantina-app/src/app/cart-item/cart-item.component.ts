import {Component, Input, OnInit} from '@angular/core';
import {Product} from "../models/product";
import {CartService} from "../services/cart.service";

@Component({
  selector: 'tr[app-cart-item]',
  templateUrl: './cart-item.component.html',
  styleUrls: ['./cart-item.component.css']
})
export class CartItemComponent implements OnInit {

  @Input()
  item: Product | any;

  count = 1;
  // @ts-ignore
  price;

  constructor(private cartService: CartService) {
  }

  ngOnInit(): void {

    this.price = this.item.foodProduct.price;
    this.count = this.item.qty;
    if(this.item.foodProduct.qty==0){
      this.deleteCartItem();
    }
  }

  changeCount() {
    this.count = Number((<HTMLInputElement>document.getElementById("count" + this.item.foodProduct.id)).value);
    this.cartService.updateCartItem(this.item.id, this.count).subscribe((data) => {
        console.log(data);
      },
      error => {
        console.log(error);
      })
  }

  deleteCartItem() {
    this.cartService.deleteCartItem(this.item.id).subscribe((data) => {
        console.log(data);
        window.location.reload();
      },
      error => {
        console.log(error);
      });
  }
}
