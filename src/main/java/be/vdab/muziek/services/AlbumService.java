package be.vdab.muziek.services;

import be.vdab.muziek.domain.Album;
import be.vdab.muziek.domain.Track;
import org.aspectj.weaver.tools.Trace;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface AlbumService{
    Optional<Album> findById(long id);
    List<Album> findAll();
    Set<Track> findAllTracksByAlbumId(long id);
}
