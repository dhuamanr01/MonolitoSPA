import { TipoDocumento } from "./tipoDocumento";
import { Usuario } from "./usuario";

export class Cliente{

    public id: number;
    public usuario: Usuario;
    public tipoDocumento: TipoDocumento;
    public nombres: string;
    public apellidos: string;
    public direccion: string;
    public telefono1: string;
    public telefono2: string;
    public email: string;
    public paginaWeb: string;
    public numeroDoc: string;
    public fechaRegistro: string;
    public fechaModificacion: string;

}