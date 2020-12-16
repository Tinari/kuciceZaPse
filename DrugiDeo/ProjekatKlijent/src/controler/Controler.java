/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;


import domen.IDomain;
import domen.Materijal;
import domen.Mesto;
import domen.NacinIzrade;
import domen.KucicaZaPse;
import domen.Radnik;
import domen.StavkaIzrade;
import domen.TipKuciceZaPse;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import session.Session;

import transfer.Request;
import transfer.Response;
import transfer.util.Operation;
import transfer.util.ResponseStatus;

/**
 *
 * @author Win 7
 */
public class Controler {

    private static Controler instance;

    public Controler() {
    }

    public static Controler getInstance() {

        if (instance == null) {
            instance = new Controler();

        }
        return instance;
    }

    public Radnik login(Radnik radnik) throws IOException, ClassNotFoundException, Exception {

        Request request = new Request();
        request.setOperation(Operation.LOGOVANJE);
        request.setData(radnik);
        Socket socket = Session.getInstance().getSocket();
        ObjectOutputStream outSocket = new ObjectOutputStream(socket.getOutputStream());
        System.out.println("saljem sada");
        outSocket.writeObject(request);
        outSocket.flush();
        System.out.println("poslato jeee");

        ObjectInputStream inSocket = new ObjectInputStream(socket.getInputStream());
        Response response = (Response) inSocket.readObject();

        ResponseStatus status = response.getStatus();
        System.out.println("klinet je dobio radnika " + status);
        if (status == ResponseStatus.SUCCESS) {
            return (Radnik) response.getData();
        } else {
            throw (Exception) response.getError();//ovo pitajj?
        }

    }

    public List<Mesto> vratiListuMesta() throws IOException, ClassNotFoundException, Exception {
        Request request = new Request();
        request.setOperation(Operation.VRATI_LISTU_MESTA);
        request.setData(new Mesto());
        Socket socket = Session.getInstance().getSocket();

        ObjectOutputStream outSocket = new ObjectOutputStream(socket.getOutputStream());
        outSocket.writeObject(request);
        outSocket.flush();

        ObjectInputStream inSocket = new ObjectInputStream(socket.getInputStream());
        Response response = (Response) inSocket.readObject();
        ResponseStatus status = response.getStatus();
        if (status == ResponseStatus.SUCCESS) {
            return (List<Mesto>) response.getData();
        } else {
            throw (Exception) response.getError();
        }
    }

    public Radnik zapamtiRadnika(Radnik radnik) throws IOException, ClassNotFoundException, Exception {
        Request request = new Request();
        request.setOperation(Operation.ZAPAMTI_RADNIKA);
        request.setData(radnik);
        Socket socket = Session.getInstance().getSocket();
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(request);
        out.flush();

        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        Response response = (Response) in.readObject();
        ResponseStatus status = response.getStatus();
        if (status == ResponseStatus.SUCCESS) {
            return (Radnik) response.getData();
        } else {
            throw new Exception("Greska u komunikaciji!" + response.getError());
        }
    }

    public Mesto vratiMestoPoId(Long idMesta) throws IOException, ClassNotFoundException, Exception {
        Request request = new Request();
        request.setOperation(Operation.VRATI_MESTO_ID);
        request.setData(idMesta);
        Socket socket = Session.getInstance().getSocket();
        ObjectOutputStream outSocket = new ObjectOutputStream(socket.getOutputStream());
        outSocket.writeObject(request);
        outSocket.flush();

        ObjectInputStream inSocket = new ObjectInputStream(socket.getInputStream());
        Response response = (Response) inSocket.readObject();
        ResponseStatus status = response.getStatus();
        if (status == ResponseStatus.SUCCESS) {
            return (Mesto) response.getData();
        } else {
            throw (Exception) response.getError();
        }

    }

    public static Radnik azurirajRadnika(Radnik radnik) throws IOException, ClassNotFoundException, Exception {
        Request request = new Request();
        request.setOperation(Operation.AZURIRAJ_RADNIKA);
        request.setData(radnik);
        Socket socket = Session.getInstance().getSocket();
        ObjectOutputStream outSocket = new ObjectOutputStream(socket.getOutputStream());
        outSocket.writeObject(request);
        outSocket.flush();

        ObjectInputStream inSocket = new ObjectInputStream(socket.getInputStream());
        Response response = (Response) inSocket.readObject();
        ResponseStatus status = response.getStatus();
        if (status == ResponseStatus.SUCCESS) {
            return (Radnik) response.getData();
        } else {
            throw (Exception) response.getError();
        }
    }

    public List<Radnik> vratiListuRadnika() throws IOException, ClassNotFoundException, Exception {
        Request request = new Request();
        request.setOperation(Operation.VRATI_SVE_RADNIKE);
        request.setData(new Radnik());
        Socket socket = Session.getInstance().getSocket();
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(request);
        out.flush();

        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        Response response = (Response) in.readObject();
        ResponseStatus status = response.getStatus();
        if (status == ResponseStatus.SUCCESS) {
            return (List<Radnik>) response.getData();
        } else {
            throw (Exception) response.getError();
        }
    }

    public Radnik nadjiRadnikaPoJmbg(String jmbg) throws IOException, ClassNotFoundException, Exception {
        Request request = new Request();
        request.setOperation(Operation.VRATI_RADNIKA_JMBG);
        request.setData(jmbg);
        Socket socket = Session.getInstance().getSocket();
        ObjectOutputStream outSocket = new ObjectOutputStream(socket.getOutputStream());
        outSocket.writeObject(request);
        outSocket.flush();

        ObjectInputStream inSocket = new ObjectInputStream(socket.getInputStream());
        Response response = (Response) inSocket.readObject();
        ResponseStatus status = response.getStatus();
        if (status == ResponseStatus.SUCCESS) {
            return (Radnik) response.getData();
        } else {
            throw (Exception) response.getError();
        }
    }

    public boolean obrisiRadnika(Radnik radnik) throws IOException, ClassNotFoundException, Exception {
        Request request = new Request();
        request.setOperation(Operation.OBRISI_RADNIKA);
        request.setData(radnik);
        Socket socket = Session.getInstance().getSocket();
        ObjectOutputStream outSocket = new ObjectOutputStream(socket.getOutputStream());
        outSocket.writeObject(request);
        outSocket.flush();

        ObjectInputStream inSocket = new ObjectInputStream(socket.getInputStream());
        Response response = (Response) inSocket.readObject();
        ResponseStatus status = response.getStatus();
        if (status == ResponseStatus.SUCCESS) {
            return (boolean) response.getData();
        } else {
            throw (Exception) response.getError();
        }
    }

    public List<TipKuciceZaPse> vratiListuTipova() throws IOException, ClassNotFoundException, Exception {
        Request request = new Request();
        request.setOperation(Operation.VRATI_SVE_TIPOVE_KUCICA_ZA_PSE);
        request.setData(new TipKuciceZaPse());
        Socket socket = Session.getInstance().getSocket();
        ObjectOutputStream outSocket = new ObjectOutputStream(socket.getOutputStream());
        outSocket.writeObject(request);
        outSocket.flush();

        ObjectInputStream inSocket = new ObjectInputStream(socket.getInputStream());
        Response response = (Response) inSocket.readObject();
        ResponseStatus status = response.getStatus();
        if (status == ResponseStatus.SUCCESS) {
            return (List<TipKuciceZaPse>) response.getData();
        } else {
            throw (Exception) response.getError();
        }
    }

    public KucicaZaPse zapamtiKucicuZaPse(KucicaZaPse kucica) throws IOException, ClassNotFoundException, Exception {
        Request request = new Request();
        request.setOperation(Operation.ZAPAMTI_KUCICU_ZA_PSE);
        request.setData(kucica);
        Socket socket = Session.getInstance().getSocket();
        ObjectOutputStream outSocket = new ObjectOutputStream(socket.getOutputStream());
        outSocket.writeObject(request);
        outSocket.flush();

        ObjectInputStream inSocket = new ObjectInputStream(socket.getInputStream());
        Response response = (Response) inSocket.readObject();
        ResponseStatus status = response.getStatus();
        System.out.println("status kucice za pse " + status);
        if (status == ResponseStatus.SUCCESS) {
            return (KucicaZaPse) response.getData();
        } else {
            throw (Exception) response.getError();
        }

    }

    public KucicaZaPse vratiKucicuZaPsePoId(int idKucicaZaPse) throws IOException, ClassNotFoundException, Exception {
        Request request = new Request();
        request.setOperation(Operation.VRATI_KUCICU_ZA_PSE_ID);
        request.setData(idKucicaZaPse);
        Socket socket = Session.getInstance().getSocket();
        ObjectOutputStream outSocket = new ObjectOutputStream(socket.getOutputStream());
        outSocket.writeObject(request);
        outSocket.flush();

        ObjectInputStream inSocket = new ObjectInputStream(socket.getInputStream());
        Response response = (Response) inSocket.readObject();
        ResponseStatus status = response.getStatus();
        if (status == ResponseStatus.SUCCESS) {
            return (KucicaZaPse) response.getData();
        } else {
            throw (Exception) response.getError();
        }

    }

    public KucicaZaPse izmeniKucicuZaPse(KucicaZaPse kucica) throws IOException, ClassNotFoundException, Exception {
        Request request = new Request();
        request.setOperation(Operation.IZMENI_KUCICU_ZA_PSE);
        request.setData(kucica);
        Socket socket = Session.getInstance().getSocket();
        ObjectOutputStream outSocket = new ObjectOutputStream(socket.getOutputStream());
        outSocket.writeObject(request);
        outSocket.flush();

        ObjectInputStream inSocket = new ObjectInputStream(socket.getInputStream());
        Response response = (Response) inSocket.readObject();
        ResponseStatus status = response.getStatus();
        if (status == ResponseStatus.SUCCESS) {
            return (KucicaZaPse) response.getData();
        } else {
            throw (Exception) response.getError();
        }

    }

    public List<IDomain> vratiListuKucicaZaPse() throws IOException, ClassNotFoundException, Exception {
        Request request = new Request();
        request.setOperation(Operation.VRATI_SVE_KUCICE_ZA_PSE);
        request.setData(new KucicaZaPse());
        Socket socket = Session.getInstance().getSocket();
        ObjectOutputStream outSocket = new ObjectOutputStream(socket.getOutputStream());
        outSocket.writeObject(request);
        outSocket.flush();

        ObjectInputStream inSocket = new ObjectInputStream(socket.getInputStream());
        Response response = (Response) inSocket.readObject();
        ResponseStatus status = response.getStatus();
        if (status == ResponseStatus.SUCCESS) {
            return (List<IDomain>) response.getData();
        } else {
            throw (Exception) response.getError();
        }
    }

    public TipKuciceZaPse vratiTipKucicaZaPsePoId(int id) throws IOException, ClassNotFoundException, Exception {
        Request request = new Request();
        request.setOperation(Operation.VRATI_TIPOVE_KUCICA_ZA_PSE_PO_ID);
        request.setData(id);
        Socket socket = Session.getInstance().getSocket();
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(request);
        out.flush();

        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        Response response = (Response) in.readObject();

        ResponseStatus status = response.getStatus();
        if (status == ResponseStatus.SUCCESS) {
            return (TipKuciceZaPse) response.getData();
        } else {
            throw (Exception) response.getError();
        }
    }

    public boolean obrisiKucicuZaPse(KucicaZaPse vrati) throws IOException, ClassNotFoundException, Exception {
        Request request = new Request();
        request.setOperation(Operation.OBRISI_KUCICU_ZA_PSE);
        request.setData(vrati);
        Socket socket = Session.getInstance().getSocket();
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(request);
        out.flush();

        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        Response response = (Response) in.readObject();
        ResponseStatus status = response.getStatus();
        if (status == ResponseStatus.SUCCESS) {
            return (boolean) response.getData();
        } else {
            throw (Exception) response.getError();
        }
    }

    public List<Materijal> vratiListuMaterijala() throws IOException, ClassNotFoundException, Exception {
        Request request = new Request();
        request.setOperation(Operation.VRATI_MATERIJAL);
        request.setData(new Materijal());
        Socket socket = Session.getInstance().getSocket();
        ObjectOutputStream outSocket = new ObjectOutputStream(socket.getOutputStream());
        outSocket.writeObject(request);
        outSocket.flush();

        ObjectInputStream inSocket = new ObjectInputStream(socket.getInputStream());
        Response response = (Response) inSocket.readObject();
        ResponseStatus status = response.getStatus();
        if (status == ResponseStatus.SUCCESS) {
            return (List<Materijal>) response.getData();
        } else {
            throw (Exception) response.getError();
        }
    }

    public NacinIzrade zapamtiNacinIzrade(NacinIzrade nacinIzrade) throws IOException, ClassNotFoundException, Exception {
        Request request = new Request();
        request.setOperation(Operation.ZAPAMTI_NACIN_IZRADE);
        request.setData(nacinIzrade);
        Socket socket = Session.getInstance().getSocket();
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(request);
        out.flush();

        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        Response response = (Response) in.readObject();
        ResponseStatus status = response.getStatus();
        if (status == ResponseStatus.SUCCESS) {
            return (NacinIzrade) response.getData();
        } else {
            throw (Exception) response.getError();
        }
    }

    public NacinIzrade azurirajNacinIzrade(NacinIzrade nacinIzrade) throws IOException, ClassNotFoundException, Exception {
        Request request = new Request();
        request.setOperation(Operation.IZMENI_NACIN_IZRADE);
        request.setData(nacinIzrade);
        Socket socket = Session.getInstance().getSocket();
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(request);
        out.flush();

        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        Response response = (Response) in.readObject();
        ResponseStatus status = response.getStatus();
        if (status == ResponseStatus.SUCCESS) {
            return (NacinIzrade) response.getData();
        } else {
            throw (Exception) response.getError();
        }
    }

    public List<NacinIzrade> vratiListuNacinaIzrade() throws IOException, ClassNotFoundException, Exception {
        Request request = new Request();
        request.setOperation(Operation.VRATI_SVE_NACINE_IZRADE);
        request.setData(new NacinIzrade());
        Socket socket = Session.getInstance().getSocket();
        ObjectOutputStream outSocket = new ObjectOutputStream(socket.getOutputStream());
        outSocket.writeObject(request);
        outSocket.flush();

        ObjectInputStream inSocket = new ObjectInputStream(socket.getInputStream());
        Response response = (Response) inSocket.readObject();
        ResponseStatus status = response.getStatus();
        if (status == ResponseStatus.SUCCESS) {
            return (List<NacinIzrade>) response.getData();
        } else {
            throw (Exception) response.getError();
        }
    }

    public NacinIzrade vratiNacinIzradePoId(int id) throws IOException, ClassNotFoundException, Exception {
        Request request = new Request();
        request.setOperation(Operation.VRATI_NACIN_IZRADE_ID);
        request.setData(id);
        Socket socket = Session.getInstance().getSocket();
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(request);
        out.flush();

        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        Response response = (Response) in.readObject();
        ResponseStatus status = response.getStatus();
        if (status == ResponseStatus.SUCCESS) {
            return (NacinIzrade) response.getData();
        } else {
            throw (Exception) response.getError();
        }
    }

    public List<StavkaIzrade> vratiStavkeZaNacin(int id) throws IOException, ClassNotFoundException, Exception {
        Request request = new Request();
        request.setOperation(Operation.VRATI_STAVKE_ZA_NACIN_IZRADE);
        request.setData(id);
        Socket socket = Session.getInstance().getSocket();
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(request);
        out.flush();

        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        Response response = (Response) in.readObject();
        ResponseStatus status = response.getStatus();
        if (status == ResponseStatus.SUCCESS) {
            return (List<StavkaIzrade>) response.getData();
        } else {
            throw (Exception) response.getError();
        }
    }

    public List<StavkaIzrade> vratiStavkeZaNacin(NacinIzrade rec) throws IOException, ClassNotFoundException, Exception {
        Request request = new Request();
        request.setOperation(Operation.VRATI_STAVKE_ZA_NACIN_IZRADE);
        request.setData(rec);
        Socket socket = Session.getInstance().getSocket();
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(request);
        out.flush();

        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        Response response = (Response) in.readObject();
        ResponseStatus status = response.getStatus();
        if (status == ResponseStatus.SUCCESS) {
            return (List<StavkaIzrade>) response.getData();
        } else {
            throw (Exception) response.getError();
        }
    }

    public List<Radnik> vratiPoKriterijumu(Radnik radnik) throws IOException, ClassNotFoundException, Exception {
        Request request = new Request();
        request.setOperation(Operation.VRATI_TRAZENE_RADNIKE);
        request.setData(radnik);
        Socket socket = Session.getInstance().getSocket();
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(request);
        out.flush();

        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        Response response = (Response) in.readObject();
        ResponseStatus status = response.getStatus();
        if (status == ResponseStatus.SUCCESS) {
            return (List<Radnik>) response.getData();
        } else {
            throw (Exception) response.getError();
        }
    }

    public List<NacinIzrade> vratiNacineZaKuciceZaPse(KucicaZaPse kucicaZaPseRef) throws IOException, ClassNotFoundException, Exception {
        NacinIzrade nacin = new NacinIzrade();
        nacin.setKucicaZaPse(kucicaZaPseRef);
        Request request = new Request();
        request.setOperation(Operation.VRATI_TRAZENE_NACINE);
        request.setData(kucicaZaPseRef);
        System.out.println("POSLO SAM SERVERU OVU KUCICU ZA PSE "+kucicaZaPseRef.getNazivKuciceZaPse());
        Socket socket = Session.getInstance().getSocket();
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(request);
        out.flush();

        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        Response response = (Response) in.readObject();
        ResponseStatus status = response.getStatus();
        if (status == ResponseStatus.SUCCESS) {
            return (List<NacinIzrade>) response.getData();
        } else {
            throw (Exception) response.getError();
        }

    }
}
