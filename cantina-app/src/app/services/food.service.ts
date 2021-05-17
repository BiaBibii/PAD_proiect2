import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class FoodService {
  private url = 'http://localhost:8080/api/foods/';

  constructor(private http: HttpClient) {
  }

  addFoodProduct(product: any) {
    return this.http.post(this.url + 'addFoodProduct', product);
  }

  getFoodList() {
    return this.http.get(this.url + 'foodList');
  }

  getFoodProductById(id: any) {
    return this.http.get(this.url + 'getFoodProduct/' + id);
  }

  deleteFoodProduct(id: string){
    return this.http.delete(this.url + 'deleteFoodProduct/'+id);
  }

  updateFoodProduct(upitem: any, id: string){
    return this.http.post(this.url + 'updateFoodProduct/'+id,upitem);
  }

  seeOrders(){
    return this.http.get(this.url + 'seeOrders');
  }
}
