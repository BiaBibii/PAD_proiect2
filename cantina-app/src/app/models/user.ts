export class User {
  id?: number;
  email?: string;
  first_name?: string;
  last_name?: string;
  password: string;
  username: string;

  constructor(username: string,password: string,id: number, email: string, first_name: string, last_name: string) {
    this.id = id;
    this.email = email;
    this.first_name = first_name;
    this.last_name = last_name;
    this.password = password;
    this.username = username;
  }

}
