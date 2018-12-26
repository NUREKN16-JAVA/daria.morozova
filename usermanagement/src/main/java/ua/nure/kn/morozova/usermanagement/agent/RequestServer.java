package ua.nure.kn.morozova.usermanagement.agent;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import ua.nure.kn.morozova.usermanagement.User;
import ua.nure.kn.morozova.usermanagement.db.DaoFactory;
import ua.nure.kn.morozova.usermanagement.db.DatabaseException;

import java.util.*;

public class RequestServer extends CyclicBehaviour {

    public void action() {
        ACLMessage message = myAgent.receive();
        if (message != null) {
            if (message.getPerformative() == ACLMessage.REQUEST) {
                myAgent.send(createReply(message));
            } else {
                Collection<User> users = parseMessage(message);
                ((SearchAgent) myAgent).showUsers(users);
            }
        } else {
            block();
        }
    }

    private ACLMessage createReply(ACLMessage message) {
        ACLMessage reply = message.createReply();
        reply.setPerformative(ACLMessage.INFORM);
        String content = message.getContent();
        StringTokenizer tokenizer = new StringTokenizer(content, ",");
        if (tokenizer.countTokens() == 2) {
            String firstName = tokenizer.nextToken();
            String lastName = tokenizer.nextToken();
            System.out.println("REPLY first name: " + firstName);
            System.out.println("REPLY last name: " + lastName);
            Collection<User> users = null;
            try {
                users = DaoFactory.getInstance().getUserDao().find(firstName, lastName);
            } catch (DatabaseException e) {
                e.printStackTrace();
                users = new ArrayList<>(0);
            }

            StringBuffer buffer = new StringBuffer();
            for (Iterator<User> it = users.iterator(); it.hasNext();) {
                User user = it.next();
                buffer.append(user.getId()).append(",");
                buffer.append(user.getFirstName()).append(",");
                buffer.append(user.getLastName()).append(";");
            }
            reply.setContent(buffer.toString());
        }

        return reply;
    }

    private Collection<User> parseMessage(ACLMessage message) {

        Collection<User> users = new LinkedList<>();
        String content = message.getContent();

        if (content != null) {
            StringTokenizer tokenizer1 = new StringTokenizer(content, ";");
            while (tokenizer1.hasMoreTokens()) {
                String userInfo = tokenizer1.nextToken();
                StringTokenizer tokenizer2 = new StringTokenizer(userInfo, ",");
                String id = tokenizer2.nextToken();
                String firstName = tokenizer2.nextToken();
                String lastName = tokenizer2.nextToken();
                System.out.println("PARSE id: " + id);
                System.out.println("PARSE first name: " + firstName);
                System.out.println("PARSE last name: " + lastName);
                users.add(new User(new Long(id), firstName, lastName, null));
            }
        }
        return users;
    }
}
