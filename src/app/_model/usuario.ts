import { Cliente } from "./cliente";
import { Proyecto } from "./proyecto";
import { ProyectoArchivo } from "./proyectoArchivo";
import { ResetToken } from "./resetToken";
import { Role } from "./role";

export class Usuario{

    public id: number;
    public role: Role;
    public user_name: string;
    public password: string;
    public estado: boolean;
    public createdAt: string;
    public updatedAt: string;
    public tokens: ResetToken[];
    public proyectos: Proyecto[];
    public clientes: Cliente[];

}