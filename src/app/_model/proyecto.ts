import { ProyectoArchivo } from "./proyectoArchivo";
import { Usuario } from "./usuario";

export class Proyecto{

    public id: number;
    public usuario: Usuario;
    public proyecto: string;
    public descripcion: string;
    public estado: boolean;
    public fecha_expiracion: string;
    public created_at: string;
    public updated_at: string;
    public archivos: ProyectoArchivo[];

}