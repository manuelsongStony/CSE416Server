package app.server.rguscdapp;

import app.server.rguscdapp.entity.Districting;
import app.server.rguscdapp.entity.Job;
import app.server.rguscdapp.enums.Minority;

public class HttpSession {

    private app.server.rguscdapp.entity.State State;
    private app.server.rguscdapp.entity.Job Job;
    private Job filteredJob;
    private Minority minority;
    private Districting currentDistricting;


}
