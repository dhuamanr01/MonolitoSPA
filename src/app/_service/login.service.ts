import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { HOST, TOKEN_NAME } from '../_shared/var.constant';
import { AuthenticatedUser } from '../_model/authenticatedUser';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  url: string = `${HOST}`;
  constructor(private http: HttpClient) { }


  login(authenticatedUser: AuthenticatedUser){
    //const body = `grant_type=password&username=${encodeURIComponent(usuario)}&password=${encodeURIComponent(contrasena)}`;
    return this.http.post(this.url+'/login', authenticatedUser,{
      // headers : new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded; charset=UTF-8').set('Authorization', 'Basic ' + btoa(TOKEN_AUTH_USERNAME+ ':'+ TOKEN_AUTH_PASSWORD))
     });
  }


}
