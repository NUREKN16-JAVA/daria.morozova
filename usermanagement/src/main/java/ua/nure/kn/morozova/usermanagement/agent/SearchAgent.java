package ua.nure.kn.morozova.usermanagement.agent;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import ua.nure.kn.morozova.usermanagement.User;
import ua.nure.kn.morozova.usermanagement.db.DaoFactory;
import ua.nure.kn.morozova.usermanagement.db.DatabaseException;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class SearchAgent extends Agent {

    private AID[] aids;

    SearchGui gui = null;


    @Override
    protected void setup() {
        super.setup();
        System.out.println(getAID().getName() + "started");

        gui = new SearchGui(this);
        gui.setVisible(true);

        DFAgentDescription description = new DFAgentDescription();
        description.setName(getAID());

        ServiceDescription serviceDescription = new ServiceDescription();
        serviceDescription.setName("JADE-searching");
        serviceDescription.setType("searching");
        description.addServices(serviceDescription);

        try {
            DFService.register(this, description);
        } catch (FIPAException e) {
            e.printStackTrace();
        }


        addBehaviour(new TickerBehaviour(this, 10000) {


            @Override
            protected void onTick() {
                DFAgentDescription agentDescription = new DFAgentDescription();
                ServiceDescription serviceDescription = new ServiceDescription();

                serviceDescription.setType("searching");
                agentDescription.addServices(serviceDescription);

                    AID thisId = getAID();

                try {
                    DFAgentDescription[] descriptions =
                            DFService.search(myAgent, agentDescription);
                    aids = new AID[descriptions.length - 1];

                    System.out.println("AGENTS: " + descriptions.length);

                    List<AID> AllAids = new LinkedList<>();

                    for (int i = 0; i < descriptions.length; i++) {
                        DFAgentDescription description = descriptions[i];
                        if( !description.getName().equals(thisId)) {
                            AllAids.add(description.getName());
                        }
                    }
                    aids = AllAids.toArray(new AID[0]);



                } catch (FIPAException e) {
                    e.printStackTrace();
                }
            }
        });

        addBehaviour(new RequestServer());

    }

    @Override
    protected void takeDown() {
        System.out.println(getAID().getName() + "terminated");
        try {
            DFService.deregister(this);
        } catch (FIPAException e) {
            e.printStackTrace();
        }
        gui.setVisible(false);
        gui.dispose();
        super.takeDown();
    }


    public void search(String firstName, String lastName) throws SearchException {
        try {
            Collection<User> users = DaoFactory.getInstance().getUserDao().find(firstName, lastName);

            if (users.size() > 0) {
                showUsers(users);
            } else {
                addBehaviour(new SearchRequestBehaviour(aids, firstName, lastName));
            }

        } catch(DatabaseException e){
                throw new SearchException(e);
            }
    }

    public void showUsers(Collection<User> users) {
        gui.addUsers(users);
    }
}
