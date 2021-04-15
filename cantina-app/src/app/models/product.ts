export class Product {
  id?: number | undefined;
  title: string| undefined;
  category: string| undefined;
  description: string| undefined;
  carbohydrates: number| undefined;
  fats: number| undefined;
  calories: number| undefined;
  protein: number| undefined;

  price: number| undefined;
  servingWeight: number| undefined;





  foodProductImage: string|undefined;
  active: boolean |undefined;

  constructor(title:string, category:string, price:number,
              calories:number, protein: number, carbohydrates: number,
              fats: number, servingWeight: number, description: string,
              foodProductImage: string, active: boolean,id:number) {
    this.id=id;
    this.title=title;
    this.category=category;
    this.price=price;
    this.calories=calories;
    this.protein=protein;
    this.carbohydrates=carbohydrates;
    this.fats=fats;
    this.servingWeight=servingWeight;
    this.description=description;
    this.foodProductImage=foodProductImage;
    this.active=active;

  }
}
