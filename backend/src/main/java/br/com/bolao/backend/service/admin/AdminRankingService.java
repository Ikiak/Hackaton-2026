package br.com.bolao.backend.service.admin;

import br.com.bolao.backend.dto.admin.RankingLinhaDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

@Service
public class AdminRankingService {

    public List<RankingLinhaDTO> listarRanking() {
        List<UsuarioRankingMock> usuariosOrdenados = usuariosMock()
                .stream()
                .sorted(
                        Comparator.comparingInt(UsuarioRankingMock::pontuacaoTotal).reversed()
                                .thenComparing(Comparator.comparingInt(UsuarioRankingMock::placaresExatos).reversed())
                                .thenComparing(UsuarioRankingMock::criadoEm)
                )
                .toList();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        return IntStream.range(0, usuariosOrdenados.size())
                .mapToObj(index -> {
                    UsuarioRankingMock usuario = usuariosOrdenados.get(index);

                    return new RankingLinhaDTO(
                            index + 1,
                            usuario.nome(),
                            usuario.pontuacaoTotal(),
                            usuario.placaresExatos(),
                            usuario.criadoEm().format(formatter)
                    );
                })
                .toList();
    }

    private List<UsuarioRankingMock> usuariosMock() {
        return List.of(
                new UsuarioRankingMock("Ana Silva", 150, 8, LocalDate.of(2026, 2, 10)),
                new UsuarioRankingMock("João Pereira", 150, 6, LocalDate.of(2026, 2, 8)),
                new UsuarioRankingMock("Bruno Costa", 150, 8, LocalDate.of(2026, 2, 5)),
                new UsuarioRankingMock("Marcos Lima", 135, 7, LocalDate.of(2026, 2, 15)),
                new UsuarioRankingMock("Carla Souza", 120, 5, LocalDate.of(2026, 2, 20)),
                new UsuarioRankingMock("Pedro Alves", 110, 4, LocalDate.of(2026, 2, 23)),
                new UsuarioRankingMock("Lucas Santos", 95, 3, LocalDate.of(2026, 3, 1))
        );
    }

    private record UsuarioRankingMock(
            String nome,
            int pontuacaoTotal,
            int placaresExatos,
            LocalDate criadoEm
    ) {
    }
}