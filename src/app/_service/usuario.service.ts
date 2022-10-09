import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Usuario } from '../_model/usuario';
import { HOST, TOKEN_NAME } from '../_shared/var.constant';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  url: string = `${HOST}/usuario`;

  constructor(private http: HttpClient) { }

  findByUserName(userName: string){
    let access_token = sessionStorage.getItem(TOKEN_NAME);
    return this.http.get<Usuario>(this.url + '/findByUserName/'+userName, {
      headers : new HttpHeaders().set('Authorization', access_token).set('Content-Type','application/json')
    });
  }

  page(page: number, size: number){
    //let access_token = JSON.parse(sessionStorage.getItem(TOKEN_NAME)).access_token;
    let access_token = sessionStorage.getItem(TOKEN_NAME);
    return this.http.get<Usuario[]>(this.url+'/page/'+page+'/'+size,{
      headers : new HttpHeaders().set('Authorization', access_token).set('Content-Type','application/json')
    });
  }
  findAll(){
    let access_token = sessionStorage.getItem(TOKEN_NAME);
    return this.http.get<Usuario[]>(this.url+'/list', {
      headers : new HttpHeaders().set('Authorization', access_token).set('Content-Type','application/json')
    });
  }

  create(usuario: Usuario){
    let access_token = sessionStorage.getItem(TOKEN_NAME);
    return this.http.post<Usuario>(this.url, usuario, {
      headers : new HttpHeaders().set('Authorization', access_token).set('Content-Type','application/json')
    });
  }
  

  update(usuario: Usuario){
    let access_token = sessionStorage.getItem(TOKEN_NAME);
    return this.http.put<Usuario>(this.url, usuario, {
      headers : new HttpHeaders().set('Authorization', access_token).set('Content-Type','application/json')
    });
  }

  findById(id: number){
    let access_token = sessionStorage.getItem(TOKEN_NAME);
    return this.http.get<Usuario>(this.url+'/findbyid/'+id, {
      headers : new HttpHeaders().set('Authorization', access_token).set('Content-Type','application/json')
    });
  }

}