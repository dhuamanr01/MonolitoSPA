import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { HOST, TOKEN_NAME } from '../_shared/var.constant';
import { Proyecto } from '../_model/proyecto';

@Injectable({
  providedIn: 'root'
})
export class ProyectoService {

  url: string = `${HOST}/proyecto`;
  constructor(private http: HttpClient) { }

  findAll(){
    let access_token = sessionStorage.getItem(TOKEN_NAME);
    return this.http.get<Proyecto[]>(this.url+'/list', {
      headers : new HttpHeaders().set('Authorization', access_token).set('Content-Type','application/json')
    });
  }

  page(page: number, size: number){
    //let access_token = JSON.parse(sessionStorage.getItem(TOKEN_NAME)).access_token;
    let access_token = sessionStorage.getItem(TOKEN_NAME);
    return this.http.get<Proyecto[]>(this.url+'/page/'+page+'/'+size,{
      headers : new HttpHeaders().set('Authorization', access_token).set('Content-Type','application/json')
    });
  }

  create(proyecto: Proyecto){
    let access_token = sessionStorage.getItem(TOKEN_NAME);
    return this.http.post<Proyecto>(this.url, proyecto, {
      headers : new HttpHeaders().set('Authorization', access_token).set('Content-Type','application/json')
    });
  }

  update(proyecto: Proyecto){
    let access_token = sessionStorage.getItem(TOKEN_NAME);
    return this.http.put<Proyecto>(this.url, proyecto, {
      headers : new HttpHeaders().set('Authorization', access_token).set('Content-Type','application/json')
    });
  }

  findById(id: number){
    let access_token = sessionStorage.getItem(TOKEN_NAME);
    return this.http.get<Proyecto>(this.url+'/findbyid/'+id, {
      headers : new HttpHeaders().set('Authorization', access_token).set('Content-Type','application/json')
    });
  }

}
