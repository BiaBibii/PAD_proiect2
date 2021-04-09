import {Injectable} from "@angular/core";

@Injectable({
  providedIn: 'root'
})
export class LoginFormComponentsService{
  public email: string;
  public password:string;
  constructor(){
    this.email="beuka_bianka@yahoo.com";
    this.password="asdf";
  }
}
