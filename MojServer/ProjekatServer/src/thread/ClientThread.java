/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thread;

import datebase.DatabaseGeneric;
import domen.IDomain;
import domen.Mesto;
import domen.NacinIzrade;
import domen.KucicaZaPse;
import domen.Radnik;
import domen.StavkaIzrade;
import domen.TipKuciceZaPse;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import so.AbstarctGenericOperation;
import so.AzurirajNacinIzrade;
import so.AzurirajKucicuZaPse;
import so.AzurirajRadnika;
import so.ObrisiKucicuZaPse;
import so.ObrisiRadnika;
import so.PrikazNacinaIzrade;
import so.PrikazKuciceZaPse;
import so.PrikaziRadnika;
import so.VratiListuMesta;
import so.VratiSveMaterijale;
import so.VratiSveNacineIzrade;
import so.VratiSveKuciceZaPse;
import so.VratiSveRadnike;
import so.VratiSveStavke;
import so.VratiTipoveKucicaZaPse;
import so.VratiTrazeneNacine;
import so.VratiTrazeneRadnik;
import so.ZapamtiNacinIzrade;
import so.ZapamtiKucicuZaPse;
import so.ZapamtiRadnika;
import transfer.Request;
import transfer.Response;
import transfer.util.Operation;
import transfer.util.ResponseStatus;

/**
 *
 * @author Win 7
 */
public class ClientThread extends Thread {

    Socket socket;
    private Radnik ulogovaniRanik;
    ServerThread server;

    public Radnik getUlogovaniRanik() {
        return ulogovaniRanik;
    }

    public void setUlogovaniRanik(Radnik ulogovaniRanik) {
        this.ulogovaniRanik = ulogovaniRanik;
    }

    public ClientThread(Socket socket) {
        this.socket = socket;
    }

    ClientThread(Socket socketKlijent, ServerThread aThis) {
        this.socket = socketKlijent;
        this.server = aThis;
    }

    @Override
    public void run() {
        super.run(); //To change body of generated methods, choose Tools | Templates.
        while (!isInterrupted()) {
            try {
                System.out.println("cekam neke zahteve");
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                Request request = (Request) in.readObject();
                Response response = new Response();
                switch (request.getOperation()) {
                    case Operation.LOGOVANJE:

                        try {
                            Radnik radnik = (Radnik) request.getData();
                            System.out.println("ovo sam dobio " + radnik.getJmbg());
                            Radnik trazeniRadnik = new DatabaseGeneric().logovanje(radnik);
                            System.out.println("ovo je ulogovani " + trazeniRadnik.getIme());
                            ulogovaniRanik = trazeniRadnik;
                            System.out.println("prijavljeni radnik " + ulogovaniRanik.getJmbg());
                            server.dodajRadnika(this);
                            response.setStatus(ResponseStatus.SUCCESS);
                            response.setData(trazeniRadnik);

                        } catch (Exception ex) {
                            response.setStatus(ResponseStatus.ERROR);
                            response.setError(ex.getMessage());
                        }
                        break;
                    case Operation.VRATI_LISTU_MESTA:

                        try {
                            VratiListuMesta vratiMesta = new VratiListuMesta();
                            vratiMesta.templateExecute((IDomain) request.getData());
                            List<IDomain> listaZaVracanje2 = vratiMesta.getLista();
                            response.setStatus(ResponseStatus.SUCCESS);
                            response.setData(listaZaVracanje2);
                        } catch (Exception ex) {
                            response.setStatus(ResponseStatus.ERROR);
                            response.setError(ex.getMessage());
                        }
                        break;
                    case Operation.VRATI_SVE_RADNIKE:
                        try {
                            VratiSveRadnike vratiRadnike = new VratiSveRadnike();
                            vratiRadnike.templateExecute((IDomain) request.getData());
                            List<IDomain> listaZaVracanje = vratiRadnike.getLista();
                            response.setStatus(ResponseStatus.SUCCESS);
                            response.setData(listaZaVracanje);
                            System.out.println(listaZaVracanje.get(0));
                        } catch (Exception ex) {
                            response.setStatus(ResponseStatus.ERROR);
                            response.setError(ex.getMessage());
                        }
                        break;
                    case Operation.VRATI_SVE_KUCICE_ZA_PSE:
                        try {
                            VratiSveKuciceZaPse vratiKucice = new VratiSveKuciceZaPse();
                            vratiKucice.templateExecute((IDomain) request.getData());
                            List<IDomain> listaZaVracanje = vratiKucice.getLista();
                            response.setStatus(ResponseStatus.SUCCESS);
                            response.setData(listaZaVracanje);
                        } catch (Exception ex) {
                            response.setStatus(ResponseStatus.ERROR);
                            response.setError(ex);
                            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);

                        }
                        break;
                    case Operation.VRATI_SVE_NACINE_IZRADE:
                        try {
                            VratiSveNacineIzrade vratiNacine = new VratiSveNacineIzrade();
                            vratiNacine.templateExecute((IDomain) request.getData());
                            List<IDomain> listaZaVracanje = vratiNacine.getLista();
                            response.setStatus(ResponseStatus.SUCCESS);
                            response.setData(listaZaVracanje);
                        } catch (Exception ex) {
                            response.setStatus(ResponseStatus.ERROR);
                            response.setError(ex.getMessage());
                        }
                        break;
                    case Operation.VRATI_SVE_TIPOVE_KUCICA_ZA_PSE:
                        try {
                            VratiTipoveKucicaZaPse vratiTipove = new VratiTipoveKucicaZaPse();
                            vratiTipove.templateExecute((IDomain) request.getData());
                            List<IDomain> listaZaVracanje = vratiTipove.getLista();
                            response.setStatus(ResponseStatus.SUCCESS);
                            response.setData(listaZaVracanje);
                        } catch (Exception ex) {
                            response.setStatus(ResponseStatus.ERROR);
                            response.setError(ex.getMessage());
                        }
                        break;
                    case Operation.VRATI_MATERIJAL:
                        try {
                            VratiSveMaterijale vratiMaterijale = new VratiSveMaterijale();
                            vratiMaterijale.templateExecute((IDomain) request.getData());
                            List<IDomain> listaZaVracanje = vratiMaterijale.getLista();
                            response.setStatus(ResponseStatus.SUCCESS);
                            response.setData(listaZaVracanje);
                        } catch (Exception ex) {
                            response.setStatus(ResponseStatus.ERROR);
                            response.setError(ex.getMessage());
                        }
                        break;
                    case Operation.ZAPAMTI_RADNIKA:

                        try {
                            ZapamtiRadnika zapamtiRadnike = new ZapamtiRadnika();
                            zapamtiRadnike.templateExecute((IDomain) request.getData());
                            response.setStatus(ResponseStatus.SUCCESS);
                            response.setData((IDomain) request.getData());
                        } catch (Exception ex) {
                            response.setStatus(ResponseStatus.ERROR);
                            response.setError(ex.getMessage());
                        }
                        break;
                    case Operation.VRATI_MESTO_ID:
                        Long idMesto = (Long) request.getData();
                        try {
                            Mesto mesto = new Mesto();
                            mesto.setIdMesta(idMesto);
                            mesto = (Mesto) new DatabaseGeneric().pronadjiPoId(mesto);
                            response.setStatus(ResponseStatus.SUCCESS);
                            response.setData(mesto);
                        } catch (Exception ex) {
                            response.setStatus(ResponseStatus.ERROR);
                            response.setError(ex.getMessage());
                        }
                        break;
                    case Operation.OBRISI_RADNIKA:
                        try {
                            ObrisiRadnika obrisiRadnika = new ObrisiRadnika();
                            obrisiRadnika.templateExecute((IDomain) request.getData());
                            response.setStatus(ResponseStatus.SUCCESS);
                            response.setData(true);
                        } catch (Exception ex) {
                            response.setStatus(ResponseStatus.ERROR);
                            response.setError(ex.getMessage());
                        }
                        break;
                    case Operation.VRATI_RADNIKA_JMBG:
                        String id = (String) request.getData();
                        try {
                            Radnik radnik = new Radnik();
                            radnik.setJmbg(id);
                            PrikaziRadnika prikazRadnika = new PrikaziRadnika();
                            prikazRadnika.templateExecute(radnik);
                            radnik = prikazRadnika.getRadnik();
                            response.setStatus(ResponseStatus.SUCCESS);
                            response.setData(radnik);
                        } catch (Exception ex) {
                            response.setStatus(ResponseStatus.ERROR);
                            response.setError(ex.getMessage());
                        }
                        break;
                    case Operation.AZURIRAJ_RADNIKA:
                        try {
                            AbstarctGenericOperation azurirajRadnika = new AzurirajRadnika();
                            azurirajRadnika.templateExecute((IDomain) request.getData());
                            response.setStatus(ResponseStatus.SUCCESS);
                            response.setData((IDomain) request.getData());

                        } catch (Exception ex) {
                            response.setStatus(ResponseStatus.ERROR);
                            response.setError(ex.getMessage());

                        }
                        break;
                    case Operation.ZAPAMTI_KUCICU_ZA_PSE:
                        try {
                            ZapamtiKucicuZaPse zapamtiKucicu = new ZapamtiKucicuZaPse();
                            zapamtiKucicu.templateExecute((IDomain) request.getData());
                            response.setData(request.getData());
                            System.out.println("ovde je odg " + response.getData());
                            response.setStatus(ResponseStatus.SUCCESS);

                        } catch (Exception ex) {
                            response.setStatus(ResponseStatus.ERROR);
                            response.setError(ex.getMessage());
                        }
                        break;
                    case Operation.OBRISI_KUCICU_ZA_PSE:
                        try {
                            ObrisiKucicuZaPse obrisiKucicu = new ObrisiKucicuZaPse();
                            obrisiKucicu.templateExecute((IDomain) request.getData());
                            response.setData(true);
                            response.setStatus(ResponseStatus.SUCCESS);

                        } catch (Exception ex) {
                            response.setStatus(ResponseStatus.ERROR);
                            response.setError(ex.getMessage());
                        }
                        break;
                    case Operation.IZMENI_KUCICU_ZA_PSE:
                        try {
                            AzurirajKucicuZaPse azurirajKucicu = new AzurirajKucicuZaPse();
                            azurirajKucicu.templateExecute((IDomain) request.getData());
                            response.setStatus(ResponseStatus.SUCCESS);
                            response.setData((IDomain) request.getData());
                        } catch (Exception ex) {
                            response.setStatus(ResponseStatus.ERROR);
                            response.setError(ex.getMessage());
                        }
                        break;
                    case Operation.VRATI_KUCICU_ZA_PSE_ID:
                        int idNam = (int) request.getData();
                        try {
                            KucicaZaPse kzp = new KucicaZaPse();
                            kzp.setKucicaZaPseId(idNam);
                            PrikazKuciceZaPse prikazKucice = new PrikazKuciceZaPse();
                            prikazKucice.templateExecute(kzp);
                            kzp = prikazKucice.getKucica();
                            response.setData(kzp);
                            response.setStatus(ResponseStatus.SUCCESS);
                        } catch (Exception ex) {
                            response.setStatus(ResponseStatus.ERROR);
                            response.setError(ex.getMessage());
                        }
                        break;
                    case Operation.VRATI_TIPOVE_KUCICA_ZA_PSE_PO_ID:
                        int idTip = (int) request.getData();
                        try {
                            TipKuciceZaPse tip = new TipKuciceZaPse();
                            tip.setTipId(idTip);
                            tip = (TipKuciceZaPse) new DatabaseGeneric().pronadjiPoId(tip);
                            response.setData(tip);
                            response.setStatus(ResponseStatus.SUCCESS);

                        } catch (Exception ex) {
                            response.setStatus(ResponseStatus.ERROR);
                            response.setError(ex.getMessage());
                        }
                        break;
                    case Operation.ZAPAMTI_NACIN_IZRADE:
                        try {
                            ZapamtiNacinIzrade zapamtiNacin = new ZapamtiNacinIzrade();
                            zapamtiNacin.templateExecute((IDomain) request.getData());
                            response.setData(request.getData());
                            response.setStatus(ResponseStatus.SUCCESS);
                        } catch (Exception ex) {
                            response.setError(ex.getMessage());
                            response.setStatus(ResponseStatus.ERROR);
                        }
                        break;
                    case Operation.VRATI_NACIN_IZRADE_ID:
                        int idNacin = (int) request.getData();
                        try {
                            NacinIzrade nacinIzrade = new NacinIzrade();
                            nacinIzrade.setNacinId(idNacin);
                            PrikazNacinaIzrade prikazNacina = new PrikazNacinaIzrade();
                            prikazNacina.templateExecute(nacinIzrade);
                            nacinIzrade = prikazNacina.getNacinIzrade();
                            response.setData(nacinIzrade);
                            response.setStatus(ResponseStatus.SUCCESS);

                        } catch (Exception ex) {
                            response.setStatus(ResponseStatus.ERROR);
                            response.setError(ex.getMessage());
                        }
                        break;
                    case Operation.IZMENI_NACIN_IZRADE:
                        try {
                            NacinIzrade nacinn = (NacinIzrade) request.getData();
                            System.out.println("server je dobio ovaj nacin " + nacinn.getOpis());
                            AzurirajNacinIzrade azurirajNacin = new AzurirajNacinIzrade();
                            azurirajNacin.templateExecute((IDomain) request.getData());
                            response.setStatus(ResponseStatus.SUCCESS);
                            response.setData((IDomain) request.getData());
                        } catch (Exception ex) {
                            response.setStatus(ResponseStatus.ERROR);
                            response.setError(ex.getMessage());
                        }
                        break;
                    case Operation.VRATI_STAVKE_ZA_NACIN_IZRADE:
                        NacinIzrade nacin3 = (NacinIzrade) request.getData();
                        System.out.println(nacin3.getOpis());
                        try {
                            VratiSveStavke vratiStavke = new VratiSveStavke();
                            vratiStavke.templateExecute((IDomain) request.getData());
                            List<IDomain> stavke = vratiStavke.getLista();
                            response.setData(stavke);
                            response.setStatus(ResponseStatus.SUCCESS);
                        } catch (Exception ex) {
                            response.setStatus(ResponseStatus.ERROR);
                            response.setError(ex.getMessage());
                        }
                        break;
                    case Operation.VRATI_TRAZENE_RADNIKE:

                        try {
                            VratiTrazeneRadnik vratiTrazene = new VratiTrazeneRadnik();
                            vratiTrazene.templateExecute((IDomain) request.getData());
                            List<IDomain> lista = vratiTrazene.getLista();
                            response.setData(lista);
                            response.setStatus(ResponseStatus.SUCCESS);
                        } catch (Exception ex) {
                            response.setStatus(ResponseStatus.ERROR);
                            response.setError(ex.getMessage());
                        }
                        break;
                    case Operation.VRATI_TRAZENE_NACINE:

                        try {

                            VratiTrazeneNacine vratiTrazeneNacine = new VratiTrazeneNacine();
                            vratiTrazeneNacine.templateExecute((IDomain) request.getData());
                            KucicaZaPse kuc = (KucicaZaPse) request.getData();
                            System.out.println("SERVER JE DOBIO OVU KUCICU ZA PSE " + kuc.getNazivKuciceZaPse());
                            List<IDomain> lista = vratiTrazeneNacine.getLista();
                            response.setData(lista);
                            response.setStatus(ResponseStatus.SUCCESS);
                        } catch (Exception ex) {
                            response.setStatus(ResponseStatus.ERROR);
                            response.setError(ex.getMessage());
                        }
                        break;

                }

                ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                output.writeObject(response);
                output.flush();
            } catch (IOException ex) {
                Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
