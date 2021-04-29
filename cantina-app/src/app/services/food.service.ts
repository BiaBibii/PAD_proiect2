import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class FoodService {
  private url = 'http://localhost:8080/api/foods/';

  constructor(private http: HttpClient) {
  }

  addFoodProduct(product:any) {
    return this.http.post(this.url + 'addFoodProduct',product);
  }

  getFoodList() {
    return this.http.get(this.url + 'foodList');
  }

}
