import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class CartService {
  private url = 'http://localhost:8080/api/cantinaCart/';

  constructor(private http: HttpClient) {
  }

  addProductToCart(id: any) {
    console.log(id);
    // @ts-ignore
    return this.http.post(this.url + 'addProductToCartItem/' + id);
  }

  getCartItems() {
    return this.http.get(this.url + 'cart');
  }

  deleteCartItem(id: any) {
    return this.http.delete(this.url + 'deleteProductFromCart/' + id);
  }

  updateCartItem(id: any, quy: any) {
    // @ts-ignore
    return this.http.post(this.url + 'updateCartItem?qty='+quy+'&id='+id);
  }
}
