package be.vdab.muziek.services;


import be.vdab.muziek.domain.Album;
import be.vdab.muziek.domain.Track;
import be.vdab.muziek.repositories.AlbumRepository;
import org.aspectj.weaver.tools.Trace;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
class DefaulAlbumService implements  AlbumService{
private final AlbumRepository albumRepository;

    DefaulAlbumService(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Album> findById(long id) {
        return albumRepository.findById(id);
    }


    @Override
    @Transactional(readOnly = true)
    public List<Album> findAll() {

        return albumRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Set<Track> findAllTracksByAlbumId(long id) {
      return   (albumRepository.findById(id)).get().getTracks();
    }
}
