package aspects;

import java.lang.reflect.Method;
import java.util.Date;

import org.aspectj.lang.Signature;

public aspect ABMAuditoriaAspect {
	
	// POINTCUT
	public pointcut abm(Object object) :execution(void dao.BaseDAOImpl.*(..)) && args(object);
	public pointcut eliminarUsuario(Object object): execution(void dao.UsuarioDAOImpl.*(..)) && args(object);
	
	// ADVICE
	after(Object object) : abm(object){
			this.registrar(object, thisEnclosingJoinPointStaticPart.getSignature());
		}
	
	private void registrar(Object target, Signature operacion) {

		Long id = (long) 0;
		//obtener el id del object
		for (Method m : target.getClass().getMethods()) {
			if (m.getName().equals("getId")) {
				try {
					id = (Long) m.invoke(target);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		Auditoria auditoria = new Auditoria();
		auditoria.setClaseObjetivo(target.getClass().toString());
		auditoria.setFechaHora(new Date());
		auditoria.setIdEnClaseObjetivo(id);
		auditoria.setOperacion(operacion.toString());
		auditoria.guardar();
	}

}
