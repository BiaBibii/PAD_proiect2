import {Component, OnInit} from '@angular/core';
import {Product} from "../models/product";
import {FoodService} from "../services/food.service";
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";

@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.css']
})
export class AddProductComponent implements OnInit {

  product: Product | undefined;
  title: any;
  form: any = {};
  selectedFile: File | any;
  imgUrl: any;
  receivedImageData: any;
  base64Data: any;
  convertedImage: any;
  image:any={};
  constructor(private router:Router ,private foodService: FoodService, private httpClient: HttpClient) {

  }

  ngOnInit(): void {
  }

  onFileChanged(event: any) {
    // @ts-ignore
    this.selectedFile = event.target.files[0];
    let reader = new FileReader();
    reader.readAsDataURL(event.target.files[0]);
    reader.onload = (event2) => {
      this.imgUrl = reader.result;
      console.log(this.imgUrl);
    }
  }
  onUpload(id: any) {


    const uploadData = new FormData();
    uploadData.append('file', this.selectedFile, id+'.jpeg');
    this.httpClient.post('http://localhost:8080/api/foods/addImage', uploadData)
      .subscribe(
        res => {
          console.log(res);
          this.receivedImageData = res;
          this.base64Data = this.receivedImageData.pic;
          this.convertedImage = 'data:image/jpeg;base64,' + this.base64Data;
        },
        err => console.log('Error Occured duringng saving: ' + err)
      );
  }

  addProduct() {
    const uploadData = new FormData();
    uploadData.append('foodProductImage', this.selectedFile);
    this.form.foodProductImage = null;
    console.log(this.form.foodProductImage);
    this.foodService.addFoodProduct(this.form).subscribe((data:any) => {
        console.log(data);
        this.onUpload(data.id);
        this.router.navigate(['/admin']);
      },
      error => {
        console.log(error);
      });
  }

  // getProduct(){
  //   console.log("ceva");
  //   this.foodSerivce.getFoodList().subscribe(data => {
  //       console.log(data);
  //     },
  //     error => { // error path
  //       console.log(error);
  //     });
  // }

}
