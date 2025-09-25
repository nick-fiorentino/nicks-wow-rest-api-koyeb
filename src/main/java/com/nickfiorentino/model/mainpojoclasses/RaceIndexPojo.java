package com.nickfiorentino.model.mainpojoclasses;

import com.nickfiorentino.model.extrapojoclassandraceclasses.Links;

import java.util.List;

public class RaceIndexPojo {

        private Links links;
        private List<RacePojo> races;

        public Links getLinks() {
            return links;
        }

        public void setLinks(Links links) {
            this.links = links;
        }

        public List<RacePojo> getRaces() {
            return races;
        }

        public void setRaces(List<RacePojo> races) {
            this.races = races;
        }


}
