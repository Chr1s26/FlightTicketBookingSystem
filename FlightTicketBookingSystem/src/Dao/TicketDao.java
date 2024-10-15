package Dao;

import java.util.List;

import Model.Ticket;

public abstract class TicketDao extends AbstractDao<Ticket> {

	public abstract void delete(int id) ;
	public abstract List<Ticket> findticketByCustomerId(int id);

}
