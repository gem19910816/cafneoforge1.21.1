# -*- coding: utf-8 -*-
"""Check jar entries for non-forward-slash paths and asset completeness."""
import zipfile

z = zipfile.ZipFile(r'C:\Users\79662\.zcode\workspace\default\dyairdrop-neoforge-1.21.1\build\libs\dyairdrop-1.1.0.jar')
names = z.namelist()
bs = chr(92)
prefixed = [n for n in names if bs in n]
print('backslash entries:', len(prefixed))
for n in prefixed[:5]:
    print('  ', n)
bad = [n for n in names if n.startswith('assets/dyairdrop/') and (' ' in n)]
print('space-in-name entries:', len(bad))
for n in bad[:5]:
    print('  ', n)
# asset summary
for kind in ['geo/', 'animations/', 'textures/', 'models/', 'blockstates/', 'sounds/', 'lang/']:
    c = len([n for n in names if 'assets/dyairdrop/' + kind in n])
    print('%-14s %d' % (kind, c))
