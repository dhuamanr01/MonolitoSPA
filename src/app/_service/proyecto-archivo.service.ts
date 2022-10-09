import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ProyectoArchivo } from '../_model/proyectoArchivo';
import { HOST, TOKEN_NAME } from '../_shared/var.constant';

@Injectable({
  providedIn: 'root'
})
export class ProyectoArchivoService {

  url: string = `${HOST}/proyecto/archivo`;
  constructor(private http: HttpClient) { } 

  findByProyectoId(id : number){
    let access_token = sessionStorage.getItem(TOKEN_NAME);
    return this.http.get<ProyectoArchivo[]>(this.url+'/findbyproyectoid/'+id, {
      headers : new HttpHeaders().set('Authorization', access_token).set('Content-Type','application/json')
    });
  }

  download(id : number){
    let access_token = sessionStorage.getItem(TOKEN_NAME);
    return this.http.get<any>(this.url+'/download/stream/'+id, {
      headers : new HttpHeaders().set('Authorization', access_token)
        .set('Content-Type','application/octet-stream'),
         responseType: 'arraybuffer' as 'json'
    });
  }

  downloadBase64(id : number){
    let access_token = sessionStorage.getItem(TOKEN_NAME);
    return this.http.get<any>(this.url+'/download/base64/'+id, {
      headers : new HttpHeaders().set('Authorization', access_token)
        .set('Content-Type','application/octet-stream')
    });
  }

  uploadFile(proyectoArchivo: ProyectoArchivo){
    let access_token = sessionStorage.getItem(TOKEN_NAME);
    return this.http.post<ProyectoArchivo[]>(this.url+'/base64', proyectoArchivo, {
      headers : new HttpHeaders().set('Authorization', access_token).set('Content-Type','application/json')
    });
  }

  delete(idArchivoProyecto: number){
    let access_token = sessionStorage.getItem(TOKEN_NAME);
    return this.http.delete<any>(this.url+'/'+idArchivoProyecto, {
      headers : new HttpHeaders().set('Authorization', access_token).set('Content-Type','application/json')
    });

  }

}