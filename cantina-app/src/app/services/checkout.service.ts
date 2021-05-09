import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class CheckoutService {


  private url = 'http://localhost:8080/api/checkout/';


  constructor(private http: HttpClient) {
  }

  goToCheckout() {
    return this.http.get(this.url + 'goToCheckout');
  }

  placeOrder(){
    // @ts-ignore
    return this.http.post(this.url+'placeCommand');
  }

}
