package be.vdab.muziek.muziekcontrollers;

import be.vdab.muziek.domain.Album;
import be.vdab.muziek.domain.Artiest;
import be.vdab.muziek.domain.Track;
import be.vdab.muziek.exceptions.AlbumNietGevondenException;
import be.vdab.muziek.services.AlbumService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.hateoas.server.TypedEntityLinks;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/albums")
@ExposesResourceFor(Album.class)
class AlbumController {
    private final AlbumService albumService;
private final TypedEntityLinks.ExtendedTypedEntityLinks<Album> links;

    AlbumController(AlbumService albumService, EntityLinks links) {
        this.albumService = albumService;
        this.links = links.forType(Album.class,Album::getId);
    }


    @GetMapping("{id}")
    EntityModel<AlbumArtiest> get(@PathVariable long id){
        return albumService.findById(id)
                .map(
                album -> EntityModel.of(new AlbumArtiest(album),
                        links.linkToItemResource(album),
                        links.linkForItemResource(album).slash("tracks")
                                .withRel("tracks")
                        )
        ).orElseThrow(AlbumNietGevondenException::new);

    }

    private static final class AlbumArtiest{
        private final String album;
        private final String artiest;

        AlbumArtiest(Album album){
            this.album=album.getNaam();
            var arti= album.getArtiest();
            this.artiest=arti.getNaam();
        }

        public String getAlbum() {
            return album;
        }

        public String getArtiest() {
            return artiest;
        }
    }


    @GetMapping("{id}/tracks")
    CollectionModel<Track> getTracksByAlbumId(@PathVariable long id){
        return
                albumService.findById(id).map(
                        tracklist->CollectionModel.of(tracklist.getTracks())
                                .add(links.linkForItemResource(tracklist)
                                        .slash("tracks").withRel("self"))
                                .add(links.linkToItemResource(tracklist).withRel("album")))
                .orElseThrow(AlbumNietGevondenException::new);



    }

/**
    @GetMapping("{id}/tracks")
   CollectionModel<Track> getTracksByAlbumId(@PathVariable long id){
return CollectionModel.of(
        albumService.findAllTracksByAlbumId(id));

    }**/






}
